public int maxProfit(int[] prices) {
    int max = 0;
    int profit = 0;
    for (int i = prices.length - 1; i >= 0; i--) {
        if (max > prices[i]) {
            profit += max - prices[i];
            System.out.println(profit);
        }
        else {
            max = prices[i];
        }
    }
    return profit;
}
