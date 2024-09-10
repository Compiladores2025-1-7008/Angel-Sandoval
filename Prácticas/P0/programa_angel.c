#include <stdio.h>

//Programa para la primera practica de Compiladores, el programa utiliza instrucciones del preprocesador para definir macros, entre otras cosas.
//El principal motivo del codigo es  conocer y aprender a usar las instrucciones del preprocesador.

//El codigo pregunta que edad tiene el usuario y despliega un mensaje dependiendo de cual sea la respuesta


#define EDAD_INT 1
#define EDAD_FLOAT 0

#if EDAD_INT
    #define edad_tipo int
    #define FORMATO "%i"
#elif EDAD_FLOAT
    #define edad_tipo float
    #define FORMATO "%f"
#else
    #error "Tipo de dato no soportado para edad"
#endif

#define saludo "Bienvenido a mi programa para Compiladores, por favor ingresa tu edad"
#define frase1 "Todavía te queda tiempo"
#define frase2 "Buenas tardes mi lic"
#define temporal "este frase será eliminada"

#undef temporal

#warning "Este programa solo tiene fines educativos"


int main(void) {
    edad_tipo edad;

    printf("%s\n", saludo);
    printf("Por favor ingresa tu edad:");
    scanf(FORMATO, &edad);

    #if EDAD_INT
        if (edad < 30) {
            printf("%s\n", frase1);
        } else {
            printf("%s\n", frase2);
        }
    #elif EDAD_FLOAT
        if (edad < 30.0) {
            printf("%s\n", frase1);
        } else {
            printf("%s\n", frase2);
        }
    #endif

    return 0;
}
