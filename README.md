# functional-kotlin

[![](https://jitpack.io/v/alexandrepiveteau/functional-kotlin.svg)](https://jitpack.io/#alexandrepiveteau/functional-kotlin)

This repository contains some utilies for functional programming in the Kotlin programming language.
The OSS license can be found in the [LICENSE.md](LICENSE.md) file of the repository.

## Installation
This library is available on [JitPack.io](https://jitpack.io/#alexandrepiveteau/functional-kotlin). Make
sure to add the following Maven repository in your root **build.gradle** file :

```
allprojects {
	repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

You can now add the library modules in your application **build.gradle** file :

```
dependencies {
	implementation "com.github.alexandrepiveteau.functional-kotlin:functional-composition:0.1.0"
	implementation "com.github.alexandrepiveteau.functional-kotlin:functional-monads:0.1.0"
}
```

## Usage
The library contains the following modules :

- **functional-composition** - Offers some infix operators for function composition.
- **functional-monads** - Offers some implementations for the `Maybe<T>`, `Either<E, V>` and `Reader<C, R>` monads.

### functional-composition

The library offers the `compose`, `andThen` and `forwardCompose` operators, as well as a handy `..` syntax for function composition.
The functions `compose` and `..` have the same effect. The functions `andThen` and `forwardCompose` have the same effect as well, and
do composition in the opposite direction as `compose`/`..`.

The infix operators are quite straightforward to use :

```kotlin
val generateNumber : () -> Int = { 42 }
val doubleNumber: (Int) -> Int = { it * 2 }

val generateDoubleNumber = generateNumber forwardCompose doubleNumber

println(generateDoubleNumber()) // Prints "84".
```

### functional-monads
#### Maybe<T>
Let's start with the `Maybe<T>` monad :

```
data class Capital(val country: String, val name: String)

val response: Maybe<List<Capital>> = emptyMaybe() // For example, might be a web API callback.

val message = response.map { it.singleOrNull { it.country == "Switzerland" } }
	.flatMap { it.toMaybe() }
	.fold({ (_, name) -> "Found the capital of Switzerland : $name." },
			{ "No capital found for Switzerland." })
        
println(message) // Prints "No capital found for Switzerland."
```

As you can see in the previous example, multiple operators are provided for the `Maybe<T>` type. The functions `map`
and `flatMap` are present, as well as a `fold`function to accumulate the result of the `Maybe<T>`. You'll also notice
that a convenience `toMaybe()` extension function can be used to transform any instance to a `Maybe<T>`.

You can always create some `Maybe<T>` by using the `maybeOf<T>(...)` and `emtpyMaybe<T>()` functions.