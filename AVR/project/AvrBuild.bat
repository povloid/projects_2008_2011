@ECHO OFF
"D:\Program Files\Atmel\AVR Tools\AvrAssembler2\avrasm2.exe" -S "D:\project\labels.tmp" -fI -W+ie -C V2E -o "D:\project\HelloLCD.hex" -d "D:\project\HelloLCD.obj" -e "D:\project\HelloLCD.eep" -m "D:\project\HelloLCD.map" "D:\partp\main.S"
