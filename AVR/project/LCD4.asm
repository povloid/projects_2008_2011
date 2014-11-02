.include "m8def.inc"

; Bus defines

;;.equ	LCD_RS	= 1
;;.equ	LCD_RW	= 2
;;.equ	LCD_E	= 3

.def temp = r16
.def razr0 = r17
.def razr1 = r18
.def razr2 = r19
.org 0

rjmp reset

reset:
	ldi	temp, low(RAMEND)
	out	SPL, temp
	ldi	temp, high(RAMEND)
	out	SPH, temp
	
	rcall LCD_init


;; MAIN FUNCTION *****************************************************************************************
main:
	
	;; Просто мигание светодиода 
	;;sbi PORTD, 0

	;;ldi razr2, $05
	;;ldi razr1, $fa
	;;ldi razr0, $00
	;;rcall delay

	;;cbi PORTD, 0

	;;ldi razr2, $05
	;;ldi razr1, $fa
	;;ldi razr0, $00
	;;rcall delay

	rjmp	main
;; MAIN FUNCTION *****************************************************************************************


;; Инициализация LCD монитора
LCD_init:

	sbi	PORTC, 0
    sbi PORTC, 1

	cbi	PORTC, 0
    cbi PORTC, 1
	
	rcall pau2000
	ldi temp, 0b00000011
	rcall pout

	rcall pau2000
	ldi temp, 0b00000010
	rcall pout
	ldi temp, 0b00001100
	rcall pout   

	rcall pau2000
	ldi temp, 0b00000010
	rcall pout
	ldi temp, 0b00001100
	rcall pout  

	rcall pau2000
	ldi temp, 0b00000000
	rcall pout
	ldi temp, 0b00001111
	rcall pout  

	rcall pau2000
	ldi temp, 0b00000000
	rcall pout
	ldi temp, 0b00000001
	rcall pout

	rcall pau2000
	ldi temp, 0b00000000
	rcall pout
	ldi temp, 0b00000111
	rcall pout
ret

pout:
	;; E=true -----------
	sbi PORTC, 2
	sbi PORTD, 0
	rcall pau

	;; RS R/W -----------
	sbrc r16, 7  ;; 5
    sbi PORTC, 0
    sbrs r16, 7
    cbi PORTC, 0
    
    sbrc r16, 6 ;; 4
    sbi PORTC, 1
    sbrs r16, 6
    cbi PORTC, 1

	rcall pau

	;; DATA -------------
    sbrc r16, 3  ;; 7
    sbi PORTD, 2
    sbrs r16, 3
    cbi PORTD, 2
    
    sbrc r16, 2  ;; 6
    sbi PORTC, 5
    sbrs r16, 2
    cbi PORTC, 5
    
    sbrc r16, 1  ;; 5
    sbi PORTC, 4
    sbrs r16, 1
    cbi PORTC, 4
    
    sbrc r16, 0  ;; 4
    sbi PORTC, 3
    sbrs r16, 0
    cbi PORTC, 3
   
   	;; E=false --------
   	rcall pau
   	cbi PORTC, 2
	cbi PORTD, 0
	rcall pau
ret

;; -------------- функции пауз 
delay:
	subi razr0,1
	sbci razr1,0
	sbci razr2,0
	brcc delay
ret

pau:
	ldi razr2, $00
	ldi razr1, $02
	ldi razr0, $80
	rcall delay
ret

pau2:
	ldi razr2, $00
	ldi razr1, $00
	ldi razr0, $f1
	rcall delay
ret

pau2000:
	ldi razr2, $00
	ldi razr1, $fa
	ldi razr0, $00
	rcall delay
ret







