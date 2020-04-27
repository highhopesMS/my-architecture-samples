package com.highhopes.myapplication.extensions

import kotlinx.coroutines.Job

fun Job?.cancelIfActive() {
    if (this?.isActive == true) {
        cancel()
    }
}
