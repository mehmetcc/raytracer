package render

/**
 * DAVID DAVENPORT'UN ASKERLERİYİZ
 *
 * @author Mehmet Can Altuntaş
 * github.com/mehmetcc
 */

case class Color(val red: Double, val green: Double, val blue: Double) {

  /**
   * TODO implement more concise polymorphism for <Color> and <Vector3D>
   */

  lazy val ir: Int = toInt(red)
  lazy val ig: Int = toInt(green)
  lazy val ib: Int = toInt(blue)

  /** Vector on Vector Action */
  def +(other: Color): Color =
    Color(red + other.red, green + other.green, blue + other.blue)

  def -(other: Color): Color = this + (other ** -1)

  def *(other: Color): Color =
    Color(red * other.red, green * other.green, blue * other.blue)

  def /(other: Color): Color =
    Color(red / other.red, green / other.green, blue / other.blue)

  /** Vector-Scalar operations */
  def **(scalar: Double): Color =
    Color(red * scalar, green * scalar, blue * scalar)

  def */(scalar: Double): Color = this ** (1 / scalar)

  /** Auxiliaries */
  private def toInt(double: Double): Int = (double * 255.9).toInt

  override def toString: String = ir + " " + ig + " " + ib

  def sample: Color = this */ Math.sqrt(red*red + green*green + blue*blue)
}
