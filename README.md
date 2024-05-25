# Árbol AVL en Java

Este proyecto implementa un Árbol AVL en Java. Un Árbol AVL es una estructura de datos de tipo árbol binario de búsqueda balanceado. La sigla "AVL" proviene de los apellidos de los inventores de la estructura, Adelson-Velsky y Landis.

## ¿Qué es un Árbol AVL?

Un Árbol AVL es una forma especial de árbol binario de búsqueda autobalanceado. La diferencia de alturas entre los subárboles izquierdo y derecho de cada nodo (llamada factor de equilibrio) está limitada a uno. Esto garantiza que la altura del árbol esté siempre logarítmicamente equilibrada con respecto al número de elementos almacenados, logrando un rendimiento de búsqueda eficiente.

## Funcionalidades implementadas:

- **inserta(T elemento)**: Inserta un nuevo elemento en el árbol manteniendo el balance AVL.
- **borra(T elemento)**: Elimina un elemento del árbol manteniendo el balance AVL.
- **contiene(T elemento)**: Verifica si un elemento está presente en el árbol.
- **borraNodo(NodoAVL<T> nodo)**: Método auxiliar para eliminar un nodo del árbol manteniendo el balance AVL.

## Cómo usar esta implementación

Para utilizar esta implementación del Árbol AVL en un proyecto Java, deben seguirse los siguientes pasos:

1. **Incorporar las clases**: Tanto la clase `AVLTree<T>` como la clase `NodoAVL<T>` deben estar incorporadas en el proyecto. Ambas son esenciales para que el árbol AVL funcione correctamente.

2. **Crear una instancia de AVLTree**:
   ```java
   AVLTree<Integer> avlTree = new AVLTree<>();
   ```
   Aquí se crea un árbol AVL que almacena elementos de tipo `Integer`. Puede reemplazarse `Integer` con cualquier tipo que implemente la interfaz `Comparable<T>`.

3. **Insertar elementos**:
   ```java
   avlTree.inserta(10);
   avlTree.inserta(20);
   avlTree.inserta(5);
   ```
   El método `inserta` se utiliza para añadir elementos al árbol. El árbol se ajustará automáticamente para mantener el balance AVL.

4. **Eliminar elementos**:
   ```java
   avlTree.borra(10);
   ```
   El método `borra` se utiliza para eliminar elementos del árbol. El árbol se reajustará para mantener el balance AVL.

5. **Verificar la existencia de un elemento**:
   ```java
   boolean existe = avlTree.contiene(20);
   ```
   El método `contiene` se utiliza para comprobar si un elemento está presente en el árbol.

--- 

Con esta implementación de Árbol AVL en Java, pueden mantenerse datos ordenados de manera eficiente y pueden realizarse operaciones de inserción y eliminación con un tiempo de ejecución óptimo.
