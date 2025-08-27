package com.vaasudev.kmptest.domain.ktor

class KtorUtility {
    object Client {
        const val HTTP_CLIENT_TIMEOUT = 60_000
    }

    object ApiHeaderKey {
        const val KEY = "key"
    }

    object ApiHeaderValue {
        const val KEY_VALUE = "SG*#2025@Gate#"
    }

    object MiddlePoint {
        const val AUTH = "customer/Auth/"
    }

    object EndPoint {
        const val INIT = MiddlePoint.AUTH.plus("init_new/")
    }

    object Param {}
}