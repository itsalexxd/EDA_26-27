# EDA – Práctica I: Asignación de Servicios (I)

Estructuras de Datos y Algoritmos · Curso 2026/27

Aplicación Java que resuelve dos problemas sobre la red de carreteras de una zona geográfica (Castilla y León) y mide el tiempo de ejecución de cada etapa para estimar su complejidad temporal.

**Autores:** _<Alejandro García Lavandera>, <Manuel Arribas Martinez>_

## Descripción

Dada una zona con sus cruces, tramos de carretera, núcleos de población y posibles puntos de servicio sanitario, el programa:

1. **Lee** los ficheros de datos de la zona.
2. **Calcula las listas de adyacencia** del grafo de carreteras.
3. **Problema 1:** asigna a cada punto de servicio el cruce más cercano geográficamente.
4. **Problema 2:** asigna a cada núcleo de población el servicio más cercano **por carretera** (tiempo mínimo), usando Dijkstra.

El objetivo de la práctica es analizar cómo escala el tiempo de cada etapa con el tamaño de la entrada (número de cruces), usando las estructuras de datos y algoritmos "obvios" que indica el enunciado.

## Estructura del proyecto

```
EDA_Practica_1/
├── CodigoPractica1/     Clases proporcionadas (no modificar)
│   ├── Cruce.java
│   ├── Tramo.java
│   ├── Nucleo.java
│   ├── Servicio.java
│   └── SolucionAbs.java
├── Solucion.java        Implementa CreaAdyacencia, Problema1, Dijkstra y Problema2
├── ListaOrd.java        Conjunto Q de Dijkstra (array parcialmente lleno y ordenado)
├── Evaluador.java       Clase principal: ejecuta las etapas y mide tiempos
└── Dato/                Datos de cada zona (Zona1 ... Zona7), un subdirectorio por zona
```

| Clase | Función |
|---|---|
| `Solucion` | Hereda de `SolucionAbs`. Listas de adyacencia, primer y segundo problema, Dijkstra. |
| `ListaOrd` | Cola de Dijkstra implementada con un array ordenado de mayor a menor según `dist`. |
| `Evaluador` | `main`: pide directorio, repeticiones y escritura de resultados; muestra tiempos medios. |

## Requisitos

- **JDK 16 o superior** (`SolucionAbs` usa `Stream.toList()`).
- Los ficheros de datos de cada zona, con `cruces.txt`, `tramos.txt`, `nucleos.txt` y `servicios.txt` directamente dentro del directorio de la zona.

## Compilación y ejecución

Desde la raíz del proyecto:

```bash
javac -d out *.java CodigoPractica1/*.java
java -cp out Evaluador
```

El programa pregunta:

```
Directorio de datos: Dato/Zona1
Numero de repeticiones: 5
Escribir resultados a fichero (s/n): n
```

Al terminar muestra el tiempo medio de cada etapa: lectura de ficheros, listas de adyacencia, primer problema y segundo problema.

> Todos los ficheros `.java` deben estar en el mismo paquete (el paquete por defecto, sin línea `package`), porque `SolucionAbs` accede a sus atributos sin modificador de acceso.

## Comprobación de resultados

Con `s` en la última pregunta se generan `res_ser.txt` y `res_nuc.txt` en el directorio de la zona. Se comparan con las soluciones oficiales:

```bash
diff --strip-trailing-cr Dato/Zona1/res_ser.txt Dato/Zona1/sol_ser.txt
diff --strip-trailing-cr Dato/Zona1/res_nuc.txt Dato/Zona1/sol_nuc.txt
```

`--strip-trailing-cr` evita falsos positivos por saltos de línea de Windows (`\r\n`) en los ficheros solución.

Para las mediciones de tiempo, responde `n` a la escritura de resultados.

## Complejidad esperada (a confirmar con las mediciones)

| Etapa | Orden previsto |
|---|---|
| Lectura de ficheros | lineal en el tamaño de los datos |
| Listas de adyacencia | O(N<sub>t</sub>) |
| Problema 1 | O(N<sub>s</sub> · N<sub>c</sub>) |
| Problema 2 | O(N<sub>n</sub> · (N<sub>c</sub>² + N<sub>t</sub> · N<sub>c</sub> + N<sub>s</sub>)) |

donde N<sub>c</sub>, N<sub>t</sub>, N<sub>n</sub> y N<sub>s</sub> son el número de cruces, tramos, núcleos y servicios. Los resultados experimentales, las gráficas y la extrapolación a la Zona 8 se recogen en el informe de la práctica.

## Estado

- [x] Lectura de ficheros y listas de adyacencia
- [x] Problema 1 (validado con la Zona 1)
- [ ] `ListaOrd`
- [ ] Dijkstra y Problema 2 (validación contra `sol_nuc.txt`)
- [ ] Mediciones (Zonas 1–7) e informe

## Entrega

Informe en PDF más los ficheros `Solucion.java`, `ListaOrd.java` y `Evaluador.java` en el Campus Virtual (fecha límite: domingo 8 de noviembre, 23:59). Defensa presencial del 9 al 13 de noviembre.



   📄 [Enunciado completo de la práctica](docs/Enunciado.pdf)
