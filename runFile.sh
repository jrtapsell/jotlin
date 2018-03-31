#!/bin/bash
docker run --rm -ti -v "${PWD}":/dir:ro jrtapsell/jotlin $1
