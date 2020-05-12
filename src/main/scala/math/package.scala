/**
 * DAVID DAVENPORT'UN ASKERLERİYİZ
 *
 * @author Mehmet Can Altuntaş
 * github.com/mehmetcc
 */
package object math {

  /**
   * Since Scala 2 doesn't support top-level type decl.
   * This is the necessary evil
   */
  type Point = Vector3D // class Point(..)
  val Point = Vector3D // object Point {}
}
