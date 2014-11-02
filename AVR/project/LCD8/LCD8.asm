
.include "m8def.inc"

;; Нужн для пауз и задержек
.def razr0 = r17
.def razr1 = r18
.def razr2 = r19
;; ------------------------


.org 0


.equ 	LCD_CLR          	= 0      ; DB0: clear display
.equ 	LCD_HOME         	= 1      ; DB1: return to home position

.equ  	LCD_ENTRY_MODE   	= 2      ; DB2: set entry mode
.equ 	LCD_ENTRY_INC    	= 1      ; DB1: increment
.equ 	LCD_ENTRY_SHIFT  	= 0      ; DB2: shift

.equ 	LCD_ON		      	= 3      ; DB3: turn lcd/cursor on
.equ  	LCD_ON_DISPLAY   	= 2      ; DB2: turn display on
.equ  	LCD_ON_CURSOR     	= 1      ; DB1: turn cursor on
.equ  	LCD_ON_BLINK      	= 0      ; DB0: blinking cursor

.equ  	LCD_MOVE          	= 4      ; DB4: move cursor/display
.equ 	LCD_MOVE_DISP       = 3      ; DB3: move display (0-> move cursor)
.equ  	LCD_MOVE_RIGHT      = 2      ; DB2: move right (0-> left)

.equ  	LCD_F		        = 5      ; DB5: function set
.equ 	LCD_F_8B		   	= 4      ; DB4: set 8BIT mode (0->4BIT mode)
.equ  	LCD_F_2L			= 3      ; DB3: two lines (0->one line)
.equ  	LCD_F_10D			= 2      ; DB2: 5x10 font (0->5x7 font)
.equ  	LCD_CGRAM           = 6      ; DB6: set CG RAM address
.equ  	LCD_DDRAM           = 7      ; DB7: set DD RAM address
 
.equ	SCR_L				= 0b00011000
.equ	SCR_R				= 0b00011100
			
.equ	CUR_L				= 0b00010000 
.equ	CUR_R				= 0b00010100


;; Мапирование выводов и портов ----------------------------------------

;; контрольный порт
.equ	CMD_PORT	= PORTC	; LCD Control Port
.equ	CMD_PIN		= PINC
.equ	CMD_DDR		= DDRC
.equ	RS			= 0
.equ	RW			= 1
.equ	E			= 2


;; порт данных ------------------
;; DB0
.equ	DATA_PORT_DB0 = PORTC
.equ	DATA_DDR_DB0 = DDRC
.equ	DB0 = 3

;; DB1
.equ	DATA_PORT_DB1 = PORTC
.equ	DATA_DDR_DB1 = DDRC
.equ	DB1 = 4

;; DB2
.equ	DATA_PORT_DB2 = PORTC
.equ	DATA_DDR_DB2 = DDRC
.equ	DB2 = 5

;; DB3
.equ	DATA_PORT_DB3 = PORTD
.equ	DATA_DDR_DB3 = DDRD
.equ	DB3 = 1

;; DB4
.equ	DATA_PORT_DB4 = PORTD
.equ	DATA_DDR_DB4 = DDRD
.equ	DB4 = 0

;; DB5
.equ	DATA_PORT_DB5 = PORTD
.equ	DATA_DDR_DB5 = DDRD
.equ	DB5 = 2

;; DB6
.equ	DATA_PORT_DB6 = PORTD
.equ	DATA_DDR_DB6 = DDRD
.equ	DB6 = 3

;; DB7
.equ	DATA_PORT_DB7 = PORTD
.equ	DATA_DDR_DB7 = DDRD
.equ	DB7 = 4
;; Мапирование выводов и портов ...............................................

;; МАКРОСЫ -------
;Write CMD
.MACRO	WR_CMD
	LDI		R16,@0
	RCALL	cout
.ENDM

;Write Data
.MACRO	WR_DATA
	LDI		R16,@0
	RCALL	dout
.ENDM

;LCD Clear
.MACRO	LCDCLR
	LDI 	R16,(1<<LCD_CLR)
	RCALL	cout
.ENDM

;Cursor HOME
.MACRO	LCDHOME
	LDI 	R16,(1<<LCD_HOME)
	RCALL	cout
.ENDM



start:

	ldi	r16,	(RAMEND) & 0xFF
	out	SPL,	r16
	ldi	r16,	(RAMEND >> 8) & 0xFF
	out	SPH,	r16

	;; необходимо везде пробежаться по регистрам направления данных рабочих портов
	;; утанавливая мы конфигурируес порт как выход
	sbi	CMD_DDR, RS ;; R=1
	sbi	CMD_DDR, RW	;; RW=1
    sbi	CMD_DDR, E	;; E=1

	;; Настраиваем порт данных
    sbi DATA_DDR_DB0, DB0	;; db0
    sbi DATA_DDR_DB1, DB1	;; db1
    sbi DATA_DDR_DB2, DB2	;; db2
	sbi DATA_DDR_DB3, DB3	;; db3
    sbi DATA_DDR_DB4, DB4	;; db4
	sbi DATA_DDR_DB5, DB5	;; db5
	sbi DATA_DDR_DB6, DB6	;; db6
	sbi DATA_DDR_DB7, DB7	;; db7
    
    ;; Инициализация LCD ---------------------------
	call pau2000
    WR_CMD 0b00110000
    call pau2000
    
    WR_CMD 0b00110000
    call pau2000
    
    WR_CMD 0b00110000
    call pau2000
    
	;; ---------------------
    WR_CMD (1<<LCD_F)|(1<<LCD_F_8B)|(1<<LCD_F_2L)
    call pau
    
	WR_CMD  (1<<LCD_ON)|(1<<LCD_ON_DISPLAY)|(1<<LCD_ON_CURSOR)|(1<<LCD_ON_BLINK)
    call pau

	WR_CMD  (1<<LCD_CLR)
    call pau
	
	WR_CMD  (1<<LCD_ENTRY_MODE)|(1<<LCD_ENTRY_INC)	

	;; Необходимо выдержать паузу после инициализации	
	call pau2000

	WR_DATA 'F'
    WR_DATA '='
    WR_DATA '1'
    WR_DATA '4'
    WR_DATA '0'
    WR_DATA '0'
    WR_DATA '0'
    ;;WR_DATA ' '
    WR_DATA 'M'
    WR_DATA 'H'
    WR_DATA 'z'
    
	
	
	;;LCDCLR

main:
	;;null
	rjmp	main


cout:
    cbi CMD_PORT, RS
    sbi CMD_PORT, E
    call pout
    cbi CMD_PORT, E
    call pau
ret

dout:
    sbi CMD_PORT, RS
    sbi CMD_PORT, E
    call pout
    cbi CMD_PORT, E
    call pau
ret


; Out 1 byte to outts from R16!
pout:
    sbrc r16, 0
    sbi DATA_PORT_DB0, DB0
    sbrs r16, 0
    cbi DATA_PORT_DB0, DB0
    
    sbrc r16, 1
    sbi DATA_PORT_DB1, DB1
    sbrs r16, 1
    cbi DATA_PORT_DB1, DB1
    
    sbrc r16, 2
    sbi DATA_PORT_DB2, DB2
    sbrs r16, 2
    cbi DATA_PORT_DB2, DB2

    sbrc r16, 3 
    sbi DATA_PORT_DB3, DB3
    sbrs r16, 3 
    cbi DATA_PORT_DB3, DB3
    
    sbrc r16, 4
    sbi DATA_PORT_DB4, DB4
    sbrs r16, 4
    cbi DATA_PORT_DB4, DB4
    
    sbrc r16, 5
    sbi DATA_PORT_DB5, DB5
    sbrs r16, 5
    cbi DATA_PORT_DB5, DB5
    
    sbrc r16, 6
    sbi DATA_PORT_DB6, DB6
    sbrs r16, 6
    cbi DATA_PORT_DB6, DB6
    
    sbrc r16, 7
    sbi DATA_PORT_DB7, DB7
    sbrs r16, 7
    cbi DATA_PORT_DB7, DB7
ret


;; паузы и задержки -------------------------------
pau:
	ldi razr2, $00
	ldi razr1, $02
	ldi razr0, $80
	call delay
ret

pau2:
	ldi razr2, $00
	ldi razr1, $00
	ldi razr0, $f1
	call delay
ret

pau2000:
	ldi razr2, $00
	ldi razr1, $fa
	ldi razr0, $00
	call delay
ret

delay:
	subi razr0,1
	sbci razr1,0
	sbci razr2,0
	brcc delay
ret
	






