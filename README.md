# functional-kotlin

[![](https://jitpack.io/v/alexandrepiveteau/functional-kotlin.svg)](https://jitpack.io/#alexandrepiveteau/functional-kotlin)

This repository contains some utilies for functional programming in the Kotlin programming language.
The OSS license can be found in the LICENSE.md file of the repository.

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