@ECHO OFF
"D:\Program Files\Atmel\AVR Tools\AvrAssembler2\avrasm2.exe" -S "D:\project\LCD8\labels.tmp" -fI -W+ie -C V2E -o "D:\project\LCD8\LCD8.hex" -d "D:\project\LCD8\LCD8.obj" -e "D:\project\LCD8\LCD8.eep" -m "D:\project\LCD8\LCD8.map" "D:\project\LCD8\LCD8.asm"
