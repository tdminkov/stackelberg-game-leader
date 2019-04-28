public class BruteForceSolver implements Solver {

  @Override
  public float maximize(Model reaction) {
    float max = 0f;
    float solution = 1f;
    for (float i = 1.0f; i < 3.0f; i+= 0.005f) {
      float profit = calculateProfit(i, reaction.predict(i));
      if (profit > max) {
        max = profit;
        solution = i;
      }
    }
    return solution;
  }

  public float calculateProfit(float leader, float follower) {
    return (leader - 1.0f) * (2f - leader + 0.3f*follower);
  }
}
