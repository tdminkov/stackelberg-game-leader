public class LinearRegressionSolver implements Solver {

  private float a_1 = 2.0f;
  private float b_1 = -1;
  private float c_l = 1.0f;
  private float c_1 = 0.3f;

  @Override
  public float maximize(Model reaction) {
    float a = reaction.getA();
    float b = reaction.getB();

    float numerator = c_l * c_1 * b - a_1 - c_1 * a;
    float denominator = 2 * (b_1 + c_1 * b);

    return numerator / denominator;
  }
}
