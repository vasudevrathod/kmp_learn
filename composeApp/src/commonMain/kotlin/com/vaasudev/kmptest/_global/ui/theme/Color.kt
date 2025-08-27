package com.vaasudev.kmptest._global.ui.theme

import androidx.compose.ui.graphics.Color

// https://developer.android.com/design/ui/mobile/guides/styles/color

/** ### Primary
 * `Meaning`: The main color of your app's theme. It's used for key components like the action bar, primary buttons, and selected elements.
 *
 * `Use`: Background color for prominent UI elements, tint color for key actions.
 **/
val PrimaryLight = Color(0xFF1F2132)
val PrimaryDark = Color(0xFFC5CCFF)

/** ### OnPrimary
 * `Meaning`: The color used for text and icons displayed on top of the primary color to ensure good contrast and readability.
 *
 * `Use`: Text color on primary buttons, icons within the action bar.*/
val OnPrimaryLight = Color(0xFFFFFFFF)
val OnPrimaryDark = Color(0xFF1F2132)

/** ### PrimaryContainer
 * `Meaning`: A lighter or contrasting variation of the primary color used for backgrounds of containers that have a visual emphasis but are not the main primary surface.
 *
 * `Use`: Background for elevated cards related to primary actions, selected tab indicators.*/
val PrimaryContainerLight = Color(0xFFC2C7FF)
val PrimaryContainerDark = Color(0xFF3D3E55)

/** ### OnPrimaryContainer
 * `Meaning`: The color used for text and icons displayed on top of the primaryContainer color.
 *
 * `Use`: Text and icon color within primaryContainer elements.*/
val OnPrimaryContainerLight = PrimaryLight
val OnPrimaryContainerDark = PrimaryDark

/** ### InversePrimary
 * `Meaning`: A color that provides strong contrast with the primary color. It's often used for elements that need to stand out or indicate a reversed color scheme.
 *
 * `Use`: Background for specific UI elements that need to visually pop, like a floating action button in certain contexts.
 * */
val InversePrimaryLight = Color(0xFF343c60)
val InversePrimaryDark = Color(0xFF343c60)

/** ### Secondary
 * `Meaning`: Provides accentuation and visual interest. It's often used for less prominent UI elements.
 *
 * `Use`: Background for secondary buttons, selection controls (checkboxes, radio buttons).
 **/
val SecondaryLight = Color(0xFF939493)
val SecondaryDark = Color(0xFF939493)

/** ### OnSecondary
 * `Meaning`: The color used for text and icons displayed on top of the secondary color.
 *
 * `Use`: Text and icon color on secondary buttons.
 * */
val OnSecondaryLight = Color(0xFF939493)
val OnSecondaryDark = Color(0xFF939493)

/** ### SecondaryContainer
 * `Meaning`: A lighter or contrasting variation of the secondary color used for backgrounds of secondary containers.
 *
 * `Use`: Background for cards or surfaces related to secondary actions.
 * */
val SecondaryContainerLight = Color(0xFF343c60)
val SecondaryContainerDark = Color(0xFF343c60)

/** ### OnSecondaryContainer
 * `Meaning`: The color used for text and icons displayed on top of the secondaryContainer color.
 *
 * `Use`: Text and icon color within secondaryContainer elements.
 * */
val OnSecondaryContainerLight = Color(0xFF343c60)
val OnSecondaryContainerDark = Color(0xFF343c60)

/** ### Tertiary
 * `Meaning`: Another accent color that can be used for broader contrast and to highlight specific elements.
 *
 * `Use`: Can be used for floating action buttons, highlighting specific information, or as a contrasting accent.
 * */
val TertiaryLight = Color(0xFF343c60)
val TertiaryDark = Color(0xFF343c60)

/** ### OnTertiary
 * `Meaning`: The color used for text and icons displayed on top of the tertiary color.
 *
 * `Use`: Text and icon color on tertiary elements.
 * */
val OnTertiaryLight = Color(0xFF343c60)
val OnTertiaryDark = Color(0xFF343c60)

/** ### TertiaryContainer
 * `Meaning`: A lighter or contrasting variation of the tertiary color for tertiary containers.
 *
 * `Use`: Background for containers related to tertiary elements.
 * */
val TertiaryContainerLight = Color(0xFF343c60)
val TertiaryContainerDark = Color(0xFF343c60)

/** ### OnTertiaryContainer
 * `Meaning`: The color used for text and icons displayed on top of the tertiaryContainer color.
 *
 * `Use`: Text and icon color within tertiaryContainer elements.
 * */
val OnTertiaryContainerLight = Color(0xFF343c60)
val OnTertiaryContainerDark = Color(0xFF343c60)

/** ### Background
 * `Meaning`: The color used for the main background of your app's screens.
 *
 * `Use`: The default background color for Scaffold and other layout containers.
 * */
val BackgroundLight = Color(0xFF343c60)
val BackgroundDark = Color(0xFF343c60)

/** ### OnBackground
 * `Meaning`: The color used for text and icons displayed on top of the background color.
 *
 * `Use`: Default text color for most text elements on the background.
 * */
val OnBackgroundLight = Color(0xFF343c60)
val OnBackgroundDark = Color(0xFF343c60)

/** ### Surface
 * `Meaning`: The color used for surfaces of components, such as cards, sheets, and menus. It can have a slight elevation or visual distinction from the background.
 *
 * `Use`: Background color for Card, ModalBottomSheet, DropdownMenu.
 * */
val SurfaceLight = Color(0xFFF2F5FF)
val SurfaceDark = Color(0xFF1A1A1A)

/** ### OnSurface
 * `Meaning`: The color used for text and icons displayed on top of the surface color.
 *
 * `Use`: Default text color within components like cards and menus.
 * */
val OnSurfaceLight = PrimaryLight
val OnSurfaceDark = PrimaryDark

/** ### SurfaceVariant
 * `Meaning`: A variation of the surface color, often a bit lighter or darker, used for subtle distinctions or grouped elements.
 *
 * `Use`: Background for TextField components, dividers, grouped list items.
 * */
val SurfaceVariantLight = Color(0xFF747492)
val SurfaceVariantDark = Color(0xFF99ACCE)

/** ### OnSurfaceVariant
 * `Meaning`: The color used for text and icons displayed on top of the surfaceVariant color.
 *
 * `Use`: Hint text in TextField, icons in TextField, text color in dividers.
 * */
val OnSurfaceVariantLight = PrimaryLight
val OnSurfaceVariantDark = PrimaryDark

/** ### SurfaceTint
 * `Meaning`: A color overlay applied to surface components to indicate elevation. The intensity of the tint increases with the elevation level. By default, it's often set to the primary color.
 *
 * `Use`: Visual cue for the elevation of components like Card and ModalBottomSheet.
 * */
val SurfaceTintLight = Color(0xFF343c60)
val SurfaceTintDark = Color(0xFF343c60)

/** ### InverseSurface
 * `Meaning`: A color that provides strong contrast with the surface color. Used for elements that need to stand out on surface backgrounds.
 *
 * `Use`: Background for specific UI elements that need to visually pop on surfaces.
 * */
val InverseSurfaceLight = Color(0xFF343c60)
val InverseSurfaceDark = Color(0xFF343c60)

/** ### InverseOnSurface
 * `Meaning`: The color used for text and icons displayed on top of the inverseSurface color.
 *
 * `Use`: Text and icon color on inverse surface elements.
 * */
val InverseOnSurfaceLight = Color(0xFF343c60)
val InverseOnSurfaceDark = Color(0xFF343c60)

/** ### Error
 * `Meaning`: The color used to indicate error states in UI components.
 *
 * `Use`: Color for error text in TextField, border color for invalid input fields.
 * */
val ErrorLight = Color(0xFFCD4A4A)
val ErrorDark = Color(0xFF904646)

/** ### OnError
 * `Meaning`: The color used for text and icons displayed on top of the error color.
 *
 * `Use`: Text and icon color on error indicators.
 * */
val OnErrorLight = Color(0xFFFFFFFF)
val OnErrorDark = Color(0xFFBBBBBB)

/** ### ErrorContainer
 * `Meaning`: A lighter or contrasting variation of the error color for error containers.
 *
 * `Use`: Background for snackbars or banners displaying error messages.
 * */
val ErrorContainerLight = Color(0xFF343c60)
val ErrorContainerDark = Color(0xFF343c60)

/** ### OnErrorContainer
 * `Meaning`: The color used for text and icons displayed on top of the errorContainer color.
 *
 * `Use`: Text and icon color within error containers.
 * */
val OnErrorContainerLight = Color(0xFF343c60)
val OnErrorContainerDark = Color(0xFF343c60)

/** ### Outline
 * `Meaning`: A subtle color used for borders and outlines of components.
 *
 * `Use`: Border color for inactive TextField, dividers.
 * */
val OutlineLight = Color(0xFF343c60)
val OutlineDark = Color(0xFF343c60)

/** ### OutlineVariant
 * `Meaning`: A more subtle variation of the outline color.
 *
 * `Use`: Can be used for very subtle dividers or borders.
 * */
val OutlineVariantLight = Color(0xFF343c60)
val OutlineVariantDark = Color(0xFF343c60)

/** ### Scrim
 * `Meaning`: A semi-transparent dark color used to dim the background when modal elements like dialogs or bottom sheets are displayed.
 *
 * `Use`: Background overlay when a modal UI element is visible.
 *         */
val ScrimLight = Color(0xFF343c60)
val ScrimDark = Color(0xFF343c60)

/** ### SurfaceBright
 * `Meaning`: A brighter variation of the surface color, often used for surfaces with higher elevation.
 *
 * `Use`: Background for elevated surfaces.
 * */
val SurfaceBrightLight = Color(0xFF343c60)
val SurfaceBrightDark = Color(0xFF343c60)

/** ### SurfaceContainer
 * `Meaning`: A general container color, often similar to surface but potentially with a slightly different emphasis.
 *
 * `Use`: Background for various container components.
 * */
val SurfaceContainerLight = Color(0XFFD5DCEB)
val SurfaceContainerDark = Color(0xFFB2CBF5)

/** ### SurfaceContainerHigh
 * `Meaning`: A higher contrast variation of surfaceContainer, indicating more visual emphasis.
 *
 * `Use`: Background for more prominent containers.*/
val SurfaceContainerHighLight = Color(0xFFB8B8B8)
val SurfaceContainerHighDark = Color(0xFF343c60)

/** ### SurfaceContainerHighest
 * `Meaning`: The highest contrast variation of surfaceContainer, for maximum visual emphasis.
 *
 * `Use`: Background for the most important containers.
 * */
val SurfaceContainerHighestLight = SurfaceContainerHighLight
val SurfaceContainerHighestDark = Color(0xFF343c60)

/** ### SurfaceContainerLow
 * `Meaning`: A lower contrast variation of surfaceContainer, for less prominent containers.
 *
 * `Use`: Background for less emphasized containers.
 * */
val SurfaceContainerLowLight = Color(0xFFE9EEF9)
val SurfaceContainerLowDark = Color(0xFF343c60)

/** ### SurfaceContainerLowest
 * `Meaning`: The lowest contrast variation of surfaceContainer, often very close to the background.
 *
 * `Use`: Background for the least emphasized containers.
 * */
val SurfaceContainerLowestLight = Color(0xFF343c60)
val SurfaceContainerLowestDark = Color(0xFF343c60)

/** ### SurfaceDim
 * `Meaning`: A dimmer variation of the surface color, often used for surfaces with lower elevation.
 *
 * `Use`: Background for lower elevation surfaces.
 * */
val SurfaceDimLight = Color(0xFF343c60)
val SurfaceDimDark = Color(0xFF343c60)


val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

/** # Dark Theme */
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val TransparentColor = Color(0x00FFFFFF)
val BlackColor = Color(0xFF000000)
val WhiteColor = Color(0xFFFFFFFF)
val Gray = Color(0xffD1D1D1)
val GrayOne = Color(0xff5D5D5D)
val RedColor = Color(0xFFE25151)
val GreenColor = Color(0xFF59B359)

val BackgroundColor = Color(0xFF020A0E)
val SecondaryColor = Color(0xFF394F6B)