package math

/**
  * DAVID DAVENPORT'UN ASKERLERİYİZ
  *
  * @author Mehmet Can Altuntaş
  * github.com/mehmetcc
  */
case class Ray(val origin: Point, val direction: Vector3D) {

  def pointAt(t: Double): Vector3D = origin + direction ** t

  def toColor: Color = {
    val direction = this.direction.unit // Vector3D
    val t = 0.5 * (direction.y + 1.0)

    (Vector3D(1.0, 1.0, 1.0)**(1.0-t) + Vector3D(0.5, 0.7, 1.0)**t).toColor // Color
  }
}

case class Vector3D(x: Double, y: Double, z: Double) {

  /**
    * TODO implement more concise polymorphism for <Color> and <Vector3D>
    */
  /** Vector on Vector Action */
  def +(other: Vector3D): Vector3D =
    Vector3D(x + other.x, y + other.y, z + other.z)

  def -(other: Vector3D): Vector3D = this + (other ** -1)

  def *(other: Vector3D): Vector3D =
    Vector3D(x * other.x, y * other.y, z * other.z)

  def /(other: Vector3D): Vector3D =
    Vector3D(x / other.x, y / other.y, z / other.z)

  /** Vector-Scalar operations */
  def **(scalar: Double): Vector3D =
    Vector3D(x * scalar, y * scalar, z * scalar)

  def */(scalar: Double): Vector3D = this ** (1 / scalar)

  /** Auxiliaries (length() etc.) */
  def length: Double = Math sqrt lengthSquared

  def lengthSquared: Double = x * x + y * y + z * z

  def sum: Double = x + y + z

  def distance(other: Vector3D): Double = {
    Math.sqrt {
      (x - other.x) * (x - other.x) +
        (y - other.y) * (y - other.y) +
        (z - other.z) * (z - other.z)
    }
  }

  def dot(other: Vector3D): Double = (this * other).sum

  def cross(other: Vector3D): Vector3D =
    Vector3D(y * other.z - z * other.y,
             z * other.x - x * other.z,
             x * other.y - y * other.x)

  def unit: Vector3D = this */ this.length

  /** I hate myself for doing this */
  def toColor: Color = Color(x, y, z)

  override def toString: String = "x: " + x + " y: " + y + " z: " + z

}
