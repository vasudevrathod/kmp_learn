This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

Of course. Let's use a very practical and common scenario where the difference between hot and cold flows becomes crystal clear: **a shopping cart in an e-commerce app.**

Imagine our app has:
*   A `ProductRepository` to get data from a server.
*   A `CartViewModel` that handles all the business logic.
*   A `UI` (like an Android Activity or Jetpack Compose screen) that displays information.

---

### Project Overview
SufarCab is a ride-hailing application developed using Kotlin Multiplatform (KMP), designed to deliver a smooth experience similar to Uber.  
It provides a robust user flow starting from authentication to booking, payments, and trip history management.

### 🚀 Features:
- 📱 **Authentication**
  - Login with mobile number or email
  - OTP-based verification for secure access

- 🏠 **Home Screen**
  - Quick access to nearby cabs and services

- 🛠 **Service Screen**
  - Explore different cab and service options

- 👤 **Profile Management**
  - View and edit user profile
  - Manage account settings

- 💳 **Wallet & Payments**
  - In-app wallet for easy balance management
  - Multiple payment methods supported

- 📜 **Trip History**
  - View past rides and payment details

- ✏️ **Edit Profile**
  - Update personal information anytime

#### Screen Shots
| ![ScreenShot 1](https://github.com/vasudevrathod/kmp_learn/blob/wolfinfinity/githubAssets/ss_1.png) | ![ScreenShot 2](https://github.com/vasudevrathod/kmp_learn/blob/wolfinfinity/githubAssets/ss_2.png) | ![ScreenShot 3](https://github.com/vasudevrathod/kmp_learn/blob/wolfinfinity/githubAssets/ss_3.png) | ![ScreenShot 4](https://github.com/vasudevrathod/kmp_learn/blob/wolfinfinity/githubAssets/ss_4.png) |
| ---------------------------------------------------|----------------------------------------------------------|---------------------------------------------------|----------------------------------------------------------|

| ![ScreenShot 5](https://github.com/vasudevrathod/kmp_learn/blob/wolfinfinity/githubAssets/ss_5.png) | ![ScreenShot 6](https://github.com/vasudevrathod/kmp_learn/blob/wolfinfinity/githubAssets/ss_6.png) | ![ScreenShot 7](https://github.com/vasudevrathod/kmp_learn/blob/wolfinfinity/githubAssets/ss_7.png) | ![ScreenShot 8](https://github.com/vasudevrathod/kmp_learn/blob/wolfinfinity/githubAssets/ss_8.png) |
| ---------------------------------------------------|----------------------------------------------------------|---------------------------------------------------|----------------------------------------------------------|

## Flow in Kotlin

### - Cold Flow
### - Hot Flow
    1. State Flow
    2. Shared Flow

### **Cold Flow**
### Use Case 1: Fetching Product Details (Cold Flow)

**The Task:** When a user taps on a product, we need to fetch its detailed information (description, price, high-resolution images) from our server.

This is the **perfect use case for a Cold Flow (`flow`)**.

**Why?**
*   **It's a one-shot operation.** The action only happens when the user explicitly requests it.
*   **The data is for one specific consumer.** The product detail screen is the *only* thing that needs this specific data at this specific time.
*   **It's lazy.** We don't want to waste network and battery fetching details for a product the user never views. The flow only starts when the UI says, "I need the details now" (by calling `.collect()`).

**Code Example:**

**In the `ProductRepository`:**

```kotlin
class ProductRepository {
  // This function returns a COLD flow.
  // The code inside flow { ... } will NOT run until .collect() is called.
  fun getProductDetails(productId: String): Flow<Product> = flow {
    println("...Fetching details for $productId from network...")
    delay(1000) // Simulate network delay
    val product = Product(id = productId, name = "Juicy Apple", price = 1.99)
    emit(product) // Send the result
  }
}
```

**In the `CartViewModel`:**

```kotlin
class CartViewModel(private val repository: ProductRepository) : ViewModel() {
  fun onProductTapped(productId: String) {
    viewModelScope.launch {
      // The cold flow starts NOW, because we are collecting.
      repository.getProductDetails(productId).collect { productDetails ->
        // Update the UI with the fetched product details
      }
    }
  }
}
```

---

### Use Case 2: Managing Shopping Cart State (Hot Flow - `StateFlow`)

**The Task:** We need to keep track of the current items in the shopping cart. Multiple parts of the UI need to know about this:
1.  The little cart icon in the app's toolbar needs to show the number of items.
2.  The main shopping cart screen needs to display the list of items and the total price.

This is the **perfect use case for a Hot Flow (`StateFlow`)**.

**Why?**
*   **It represents a shared state.** The cart's contents are a single source of truth that multiple UI components need to observe.
*   **It must always have a value.** The cart is either empty or it has items. A `StateFlow` requires an initial value (e.g., an empty cart).
*   **New subscribers need the current value immediately.** When the user navigates to the cart screen, it must instantly show the current items, not wait for a new item to be added.

**Code Example:**

**In the `CartViewModel`:**

```kotlin
// Data class to represent the entire state of the cart
data class CartUiState(
  val items: List<Product> = emptyList(),
  val totalCost: Double = 0.0
)

class CartViewModel() : ViewModel() {

  // _cartState is the private, mutable hot flow. It's the "owner" of the state.
  private val _cartState = MutableStateFlow(CartUiState())

  // cartState is the public, read-only version exposed to the UI.
  val cartState: StateFlow<CartUiState> = _cartState.asStateFlow()

  fun addItemToCart(product: Product) {
    // Update the state. All collectors will automatically receive this new state.
    _cartState.update { currentState ->
      val newItems = currentState.items + product
      val newCost = newItems.sumOf { it.price }
      currentState.copy(items = newItems, totalCost = newCost)
    }
  }
}
```

**In the `UI`:**

```kotlin
// In the Toolbar
viewModel.cartState.collect { state ->
  toolbarCartIcon.text = state.items.size.toString()
}

// In the Cart Screen
viewModel.cartState.collect { state ->
  cartRecyclerView.adapter = CartAdapter(state.items)
  totalPriceTextView.text = "$${state.totalCost}"
}
```
When `addItemToCart` is called, both the toolbar icon and the cart screen update automatically.

---

### Use Case 3: Showing a "Item Added" Message (Hot Flow - `SharedFlow`)

**The Task:** After a user adds an item to the cart, we want to show a temporary message (a "Toast" or "Snackbar") that says "Apple added to cart!". This message should only be shown once per event.

This is the **perfect use case for a Hot Flow (`SharedFlow`)**.

**Why not `StateFlow`?**
If we used a `StateFlow` to hold the message, and the user rotated their phone, the UI would be recreated, re-collect the *last state* from the `StateFlow`, and show the "Item Added" message *again*. We don't want that. We need an event that is fired, consumed, and then forgotten.

**Why `SharedFlow`?**
*   **It's for one-time events.** It broadcasts an event to any active listeners.
*   **It's stateless by default.** Unlike `StateFlow`, it doesn't have a concept of a "current value." It just fires events.
*   **It's perfect for decoupling.** The ViewModel can just say "an item was added!" without knowing or caring who is listening or what they will do (e.g., show a Snackbar).

**Code Example:**

**In the `CartViewModel`:**

```kotlin
class CartViewModel() : ViewModel() {
  // ... existing StateFlow code ...

  private val _cartEvents = MutableSharedFlow<String>()
  val cartEvents: SharedFlow<String> = _cartEvents.asSharedFlow()

  fun addItemToCart(product: Product) {
    // First, update the state (StateFlow)
    _cartState.update { ... }

    // Then, fire the one-time event (SharedFlow)
    viewModelScope.launch {
      _cartEvents.emit("${product.name} added to cart!")
    }
  }
}
```

**In the `UI` (e.g., Activity's `onCreate`)**

```kotlin
// In the main Activity or Fragment
lifecycleScope.launch {
  viewModel.cartEvents.collect { message ->
    // This code only runs when a NEW event is emitted.
    Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
  }
}
```

Now, when an item is added, the Snackbar will show. If the user rotates the screen, the `collect` block will *not* be re-triggered with the old message.

### Summary Table

| Feature               | Cold Flow (`flow`)        | Hot Flow (`StateFlow`)                | Hot Flow (`SharedFlow`)                        |
|:----------------------|:--------------------------|:--------------------------------------|:-----------------------------------------------|
| **Example Use Case**  | Fetching product details. | Managing the cart's contents.         | Showing an "item added" snackbar.              |
| **Starts When...**    | `.collect()` is called.   | Immediately, has initial value.       | Immediately, but has no initial value.         |
| **Behavior**          | Lazy, one-shot operation. | Represents a shared State.            | Broadcasts one-time Events.                    |
| **Each Collector...** | Gets a new, fresh stream. | Gets the current value, then updates. | Gets new events from the moment it subscribes. |
