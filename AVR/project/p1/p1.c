#include <mega8.h>
#asm
.equ __lcd_port=0x15 ;PORTC
#endasm
#include <lcd.h>
#include<stdio.h>

char lcd_buffer[33];
unsigned long int x = 10000000;
void main(void)
{
lcd_init(16);
sprintf(lcd_buffer,"%lu",x);
lcd_puts(lcd_buffer);
lcd_gotoxy(0, 1);
lcd_putchar('W');
lcd_putchar('O');
lcd_putchar('R');
lcd_putchar('K');
lcd_putchar(' ');
lcd_putchar('I');
lcd_putchar('T');
lcd_putchar(' ');
lcd_putchar(':');
lcd_putchar(')');
}
