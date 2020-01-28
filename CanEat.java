/* CanEat.java
 * @version 1.0
 * @author Paula Yuan
 * @date Nov. 21, 2019
 * Public interface for the food consumption of creatures in Cheerville.
 */

public interface CanEat<T> {
  public void eat(T obj);
}