#!/usr/bin/env bash

set HOME = `pwd`

cd joshua-web/src/main/resources/websrc
webpack

cd $HOME
