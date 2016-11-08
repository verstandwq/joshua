#!/usr/bin/env bash

set HOME = `pwd`

cd joshua-web/src/main/resources/websrc
gulp css
webpack

cd $HOME
