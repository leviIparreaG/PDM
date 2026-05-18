# PDM - Programacion de Dispositivos Moviles

Repositorio de tareas de Android para la materia Programacion de Dispositivos Moviles.

## Integrantes
- Levi Iparrea Granados

---

## Tarea 04 — Intents entre actividades

Ubicacion: `tarea/tarea04/`

Continuacion de la Tarea 03. Se agregaron Intents para comunicar las tres actividades de la aplicacion, enviando y recibiendo valores entre ellas.

### Flujo de la aplicacion
1. **MainActivity** — El usuario ingresa nombre, telefono, extras y tipo de entrega. Al presionar "Continuar" se valida el formulario y se navega a PedidoActivity enviando los datos via Intent.
2. **PedidoActivity** — Recibe los datos de MainActivity y los muestra en un resumen. El usuario ingresa la direccion, cantidad y metodo de pago. Al confirmar, navega a ConfirmacionActivity enviando todos los datos del pedido via Intent.
3. **ConfirmacionActivity** — Recibe todos los datos del pedido y muestra el resumen completo. El boton "Hacer nuevo pedido" regresa a MainActivity limpiando el back stack.

### Tecnologias
- Kotlin
- Intent con `putExtra` / `getStringExtra` para paso de datos entre actividades
- `FLAG_ACTIVITY_CLEAR_TOP` para regresar a MainActivity desde ConfirmacionActivity
- ActionBar con Navigation Drawer (heredado de Tarea 03)
- Layouts `portrait` y `landscape` para adaptarse a rotacion de pantalla

---

## Tarea 03 — ActionBar y Navigation Drawer

Ubicacion: `tarea/tarea03/`

---

## Tarea 02

Ubicacion: `tarea/tarea02/`

---

## Ejecucion (cualquier tarea)
1. Abrir la carpeta de la tarea en Android Studio (`File > Open`).
2. Sincronizar Gradle.
3. Ejecutar en emulador o dispositivo (`minSdk 24`).
