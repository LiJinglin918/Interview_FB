// 90. Stock with Charge/fee
// maintain a veriable which is the profit we make 
// when the prices is continuously acending,
// when today's prices is lower than yesterday,
// which means we finish one transaction, 
// calculate the final profit with charge, if > 0 add to result

public int maxProfitWithCharge(int[] prices, int charge) {
    int profit = 0;
    int localProfit = 0;
    boolean yesterdaySold = false;
    for (int i = 1; i < prices.length; i++) {
        if (prices[i] > prices[i - 1]) {
            localProfit += prices[i] - prices[i - 1];
            if (!yesterdaySold) {
                localProfit -= charge;
            }
            yesterdaySold = true;
        }
        else if (yesterdaySold) {
            profit += localProfit > 0 ? localProfit : 0;
            localProfit = 0;
            yesterdaySold = false;
        }
    }
    if (localProfit > 0) {
        profit += localProfit;
    }
    return profit;
}
