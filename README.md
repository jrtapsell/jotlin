# Jotlin

## Why call it Jotlin

The name comes _Kotlin_, the language we run on, and _Jot_, as in to write something quickly.

## What this language does

This language uses logic to try to shorten kotlin code.

## What this language doesn't

To avoid falling foul of the custom language for a challenge rule we avoid making builtins for specific challenges.

## Can this be used for old questions

According to [this](https://codegolf.meta.stackexchange.com/a/7011/73772), yes.

## How to run the code

There are multiple ways to run the Jotlin runtime

### Using docker
    docker run --rm -ti -v "${PWD}":/dir:ro jrtapsell/jotlin examples/142243/input.jt

### Using the jar
    java -jar jotlin.jar run examples/142243/input.jt

## Documentation

A list of builtins is available [here](https://docs.google.com/spreadsheets/d/1QeGMf1UGDnVn-SpvPvOHDOxRXj-RipUKSovctsaU-CM/edit?usp=sharing).