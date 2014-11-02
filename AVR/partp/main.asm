
.include "m8def.inc"

; Bus defines

.equ	DBUS00	=	DDRC
.equ	DBUS01	=	DDRC
.equ	DBUS02	=	DDRC
.equ	DBUS03	=	DDRC

.equ	PBUS00	=	PORTC
.equ	PBUS01	=	PORTC
.equ	PBUS02	=	PORTC
.equ	PBUS03	=	PORTC

.equ	DBUS10	=	DDRC
.equ	DBUS11	=	DDRC
.equ	DBUS12	=	DDRD
.equ	DBUS13	=	DDRD

.equ	PBUS10	=	PORTC
.equ	PBUS11	=	PORTC
.equ	PBUS12	=	PORTD
.equ	PBUS13	=	PORTD

.equ	BBUS00	=	0
.equ	BBUS01	=	1
.equ	BBUS02	=	2
.equ	BBUS03	=	5

.equ	BBUS10	=	4
.equ	BBUS11	=	3
.equ	BBUS12	=	4
.equ	BBUS13	=	3


.org 0

start:

	ldi	r16,	(RAMEND) & 0xFF
	out	SPL,	r16
	ldi	r16,	(RAMEND >> 8) & 0xFF
	out	SPH,	r16






; BUS[0-1] to Out

	sbi	DBUS00,	BBUS00
	sbi	DBUS01,	BBUS01
	sbi	DBUS02,	BBUS02
	sbi	DBUS03,	BBUS03

	sbi	DBUS10,	BBUS10
	sbi	DBUS11,	BBUS11
	sbi	DBUS12,	BBUS12
	sbi	DBUS13,	BBUS13




main:

	rcall	phase_0_A
	rcall	pau
	rcall	phase_0_A_1
	rcall	pau
	rcall	phase_0_B
	rcall	pau
	rcall	phase_0_B_1
	rcall	pau
	rcall	phase_0_C
	rcall	pau
	rcall	phase_0_C_1
	rcall	pau
	rcall	phase_0_D
	rcall	pau
	rcall	phase_0_D_1
	rcall	pau


	rjmp	main


; Bus 0 : phase A

phase_0_A:
	sbi	PBUS00,	BBUS00
	cbi	PBUS01,	BBUS01
	cbi	PBUS02,	BBUS02
	cbi	PBUS03,	BBUS03
	ret

phase_0_A_1:
	sbi	PBUS00,	BBUS00
	sbi	PBUS01,	BBUS01
	cbi	PBUS02,	BBUS02
	cbi	PBUS03,	BBUS03
	ret

; Bus 0 : phase B

phase_0_B:
	cbi	PBUS00,	BBUS00
	sbi	PBUS01,	BBUS01
	cbi	PBUS02,	BBUS02
	cbi	PBUS03,	BBUS03
	ret

phase_0_B_1:
	cbi	PBUS00,	BBUS00
	sbi	PBUS01,	BBUS01
	sbi	PBUS02,	BBUS02
	cbi	PBUS03,	BBUS03
	ret

; Bus 0 : phase C

phase_0_C:
	cbi	PBUS00,	BBUS00
	cbi	PBUS01,	BBUS01
	sbi	PBUS02,	BBUS02
	cbi	PBUS03,	BBUS03
	ret

phase_0_C_1:
	cbi	PBUS00,	BBUS00
	cbi	PBUS01,	BBUS01
	sbi	PBUS02,	BBUS02
	sbi	PBUS03,	BBUS03
	ret

; Bus 0 : phase D

phase_0_D:
	cbi	PBUS00,	BBUS00
	cbi	PBUS01,	BBUS01
	cbi	PBUS02,	BBUS02
	sbi	PBUS03,	BBUS03
	ret

phase_0_D_1:
	sbi	PBUS00,	BBUS00
	cbi	PBUS01,	BBUS01
	cbi	PBUS02,	BBUS02
	sbi	PBUS03,	BBUS03
	ret

; Dirthy pause

pau:
	ldi	r16,	255
lpa:
	nop

	ldi	r17,	65
ppp:
	nop
	nop
	nop
	nop
	nop
	nop
	nop
	dec	r17
	tst	r17
	brne	ppp

	dec	r16
	tst	r16
	brne	lpa
	ret






