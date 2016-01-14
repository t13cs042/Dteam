package Dataclass;

import java.util.ArrayList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

//xxクラスでカインド（テーブル）を定義するための宣言
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Predict {
	
	//ID
		
		// メンバ id の値はシステムが自動的に重複しないようつける
		@PrimaryKey
		@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
		private Long id;

		//予測気候データ
		//温度
		@Persistent 
		private ArrayList<Double> temp;

		//日照量
		@Persistent 
		private ArrayList<Double> laytime;

		//降水量
		@Persistent 
		private ArrayList<Double> prec;

		//予測収穫量
		@Persistent 
		private double yield;

		//予測最大収穫量
		@Persistent 
		private double maxYield;

		//予測最小収穫量
		@Persistent 
		private double minYield;
		
		
		public Predict( ArrayList<Double> temp, ArrayList<Double> laytime, ArrayList<Double> prec, double yield, double maxYield, double minYield) {
			this.temp = temp;
			this.laytime = laytime;
			this.prec = prec;
			this.yield = yield;
			this.maxYield = maxYield;
			this.minYield = minYield;
		}


		public ArrayList<Double> getTemp() {
			return temp;
		}


		public void setTemp(ArrayList<Double> temp) {
			this.temp = temp;
		}


		public ArrayList<Double> getLaytime() {
			return laytime;
		}


		public void setLaytime(ArrayList<Double> laytime) {
			this.laytime = laytime;
		}


		public ArrayList<Double> getPrec() {
			return prec;
		}


		public void setPrec(ArrayList<Double> prec) {
			this.prec = prec;
		}


		public double getYield() {
			return yield;
		}


		public void setYield(double yield) {
			this.yield = yield;
		}


		public double getMaxYield() {
			return maxYield;
		}


		public void setMaxYield(double maxYield) {
			this.maxYield = maxYield;
		}


		public double getMinYield() {
			return minYield;
		}


		public void setMinYield(double minYield) {
			this.minYield = minYield;
		}

}
