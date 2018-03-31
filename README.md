# Jotlin
[![Build Status](https://travis-ci.org/jrtapsell/jotlin.svg?branch=master)](https://travis-ci.org/jrtapsell/jotlin)  [![Coverage Status](https://coveralls.io/repos/github/jrtapsell/jotlin/badge.svg?branch=master)](https://coveralls.io/github/jrtapsell/jotlin?branch=master) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/4dd3ac3dcf7c4d33bbdc4e5911ca41c7)](https://www.codacy.com/app/jrtapsell/jotlin?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jrtapsell/jotlin&amp;utm_campaign=Badge_Grade)

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