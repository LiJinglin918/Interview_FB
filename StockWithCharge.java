// 90. Stock with Charge/fee
// maintain a veriable which is the profit we make 
// when the prices is continuously acending,
// when today's prices is lower than yesterday,
// which means we finish one transaction, 
// calculate the final profit with charge, if > 0 add to result








/**
 * 1. use the variables to store the local and global profit.
 * 2. use a boolean to indicate whether the stock is sold before the current day
 * 3. When prices is increasing, keep calculate localProfit
 * 4. When the prices is not increasing, it means we finish one transaction. Calculate the final profit with charge. 
 *
 */

public class StockWithCharge {
	public int maxProfitWithCharge(int[] prices, int charge) {
		int res = 0;
		int localProfit = 0;
		boolean yesterdaySold = false;
		for (int i = 1; i < prices.length; i++) {
			
			// the prices is increasing
			if (prices[i] > prices[i - 1]) {
				localProfit += prices[i] - prices[i - 1];
				
				// 如果昨天没卖，今天卖。
				if (!yesterdaySold) {
					localProfit -= charge;
				}
				yesterdaySold = true;
			}
			
			// the prices is not increasing, yesterday sold.
			else if (yesterdaySold) {
				res += localProfit > 0? localProfit : 0;
				localProfit = 0;
				yesterdaySold = false;
			}
		}
		if (localProfit > 0)
			res += localProfit;
		return res;
	}
}
