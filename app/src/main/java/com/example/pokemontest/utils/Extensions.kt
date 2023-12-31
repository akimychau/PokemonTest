package com.example.pokemontest.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

fun Disposable.disposeBy(bag: CompositeDisposable) {
    bag.add(this)
}

inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)!!


fun <T : Any> Single<T>.doCompletable(
    completableCreator: (data: T) -> Completable
): Single<T> {
    return this.flatMap {
        completableCreator(it).andThen(Single.just(it))
    }
}

fun String.firstCharToUpperCase(): String = replaceFirstChar { firstChar ->
    firstChar.uppercase()
}