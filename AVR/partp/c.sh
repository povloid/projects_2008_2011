#!/bin/bash

avrdude -p m8 -c stk200 -P /dev/megadev.isp.stk200 -U flash:w:main.S.hex -V
