
.include "m8def.inc"

; Bus defines
.def razr0 = r17
.def razr1 = r18
.def razr2 = r19

.org 0

start:

	ldi	r16,	(RAMEND) & 0xFF
	out	SPL,	r16
	ldi	r16,	(RAMEND >> 8) & 0xFF
	out	SPH,	r16

    sbi DDRC, 3
    sbi DDRC, 4
    sbi DDRC, 5
	sbi DDRD, 0
    sbi DDRD, 1
	sbi DDRD, 2
	sbi DDRD, 3
	sbi DDRD, 4
    
    sbi	DDRC, 0
	sbi	DDRC, 1
    sbi	DDRC, 2

    cbi	PORTC, 0
    cbi PORTC, 2

    call init

	ldi	r16, ' '
    call dout
    ldi	r16, 'H'
    call dout
    ldi r16, 'e'
    call dout
    ldi	r16, 'l'
    call dout
    ldi r16, 'l'
    call dout
    ldi	r16, 'o'
    call dout
    ldi r16, ','
    call dout
    ldi	r16, ' '
    call dout
    ldi	r16, 'w'
    call dout
    ldi	r16, 'o'
    call dout
    ldi	r16, 'r'
    call dout
    ldi	r16, 'l'
    call dout
    ldi	r16, 'd'
    call dout
    ldi	r16, '!'
    call dout
    ldi r16, '3'
    call dout
    
	ldi r16, 0x02
    call cout
    
    ldi  r20, 0x55

main:
	rcall pau2
	sbi PORTB, 1	 
	rcall pau2
    cbi PORTB, 1	
	rjmp	main

init:
	call pau2000
    ldi r16, 0x30
    call cout
    call pau
    
    ldi r16, 0x30
    call cout
    call pau
    
    ldi r16, 0x30
    call cout
    call pau
    
    ldi r16, 0x38
    call cout
    call pau
    
    ldi r16, 0x0F
    call cout
    
    ldi r16, 0x01
    call cout
    call pau2000
    
    ldi r16, 0x06
    call cout
ret


cout:
    cbi PORTC, 0
    sbi PORTC, 2

    call pout

    cbi PORTC, 2
    call pau
ret

dout:
    sbi PORTC, 0
    sbi PORTC, 2

    call pout

    cbi PORTC, 2
    call pau
ret


; Out 1 byte to outts from R16!
pout:
    sbrc r16, 0
    sbi PORTC, 3
    sbrs r16, 0
    cbi PORTC, 3
    
    sbrc r16, 1
    sbi PORTC, 4
    sbrs r16, 1
    cbi PORTC, 4
    
    sbrc r16, 2
    sbi PORTC, 5
    sbrs r16, 2
    cbi PORTC, 5


    sbrc r16, 3 
    sbi PORTD, 1
    sbrs r16, 3 
    cbi PORTD, 1
    
    sbrc r16, 4
    sbi PORTD, 0
    sbrs r16, 4
    cbi PORTD, 0
    
    sbrc r16, 5
    sbi PORTD, 2
    sbrs r16, 5
    cbi PORTD, 2
    
    sbrc r16, 6
    sbi PORTD, 3
    sbrs r16, 6
    cbi PORTD, 3
    
    sbrc r16, 7
    sbi PORTD, 4
    sbrs r16, 7
    cbi PORTD, 4
   
ret

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
	






