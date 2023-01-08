# proyecto-04

El proyecto es un bingo. Existen dos tipos de usuarios: 
-Jugadores (para login usuario: jonlopez, contraseña lopezjon)
-Administradores (para login usuario: carmen.perez, contraseña carpez)

Secciones: 
ADMINISTRADOR

-Iniciar una partida: Primero se dejará configurar el valor del bingo (también calculado automáticamente por el número de usuarios conectados y 
un ponderador del archivo de configuración). A continuación podrá empezar una partida e ir extrayendo nuevos números. Cuando algún jugador cante bingo, se le 
dará la opción de de revisar dicho bingo. Se terminará la partida cuando el administrador pulse el botón de finalizar partida o algún bingo cantado sea correcto. 
En ese momento se dejará 
generar un archivo resumen para guardar en el ordenador. 

-Gestionar los usuarios: en esta sección se ven todos los usuarios registrados, hay opción de añadir un nuevo usuario y borrar existentes. 

-Acceder a una serie de estadísticas: una serie de estadísticas sobre el bingo son generadas

-Ver y gestionar las ligas:Los usuarios están ordenados por ligas de 10 jugadores, el administrador puede ver estas ligas y además puede actualizarlas, 
volviendo a ordenar las ligas por el bote que tienen los usuarios. 

USUARIO
Por su parte, el usuario cuando accede a la app tiene la opción de jugar. Cuando el jugador pulsa en el botón jugar y el programa revisa si existen partidas activas 
(empezadas por el administrador). Si existen partidas activas se le permitirá al jugador comprar cartones para dicha partida y a través de un hilo aparecerán 
en la pantalla los números que vayan añadiendo a la partida, tanto en una lista como en grande el último número extraído. 
Los cartones permitirán ir tachando números y cantar bingo. Al finalizar la partida (tanto si es el usuario el que ha cantado bingo, como si es otro usuario o 
si el administrador ha acabado la partida) se da un aviso y el usuario vuelve a tener la oportunidad de mirar si hay nuevas partidas activas. 

Uso práctico: una partida completa
Para ver el funcionamiento, primero ejecuta el programa y accede como administrador y empieza una partida nueva
Accede como usuario (todas las veces que quieras) y dale al botón de "jugar". Compra un cartón
El administrador podrá refrescar la configuración de partida y empezar la partida.
Como administrador ve sacando numeros y podrás ver como aparecen en el usuario. 
Cuando tengas el bingo completo, canta bingo.
En la proxima extracción de número del administrador se avisará del buingo y se comprobará. 
Finalizará la partida y al administrador se le dejará guardar un resumen de la partida. 
