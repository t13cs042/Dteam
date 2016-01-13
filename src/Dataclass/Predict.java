package Dataclass;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

public class Predict {
	
	//ID
		@PrimaryKey
		// メンバ id の値はシステムが自動的に重複しないようつける
		@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
		private Long id;

		//予測気候データ
		//温度
		@Persistent 
		private List<Double> temp;

		//日照量
		@Persistent 
		private List<Double> laytime;

		//降水量
		@Persistent 
		private List<Double> prec;

		//予測収穫量
		@Persistent 
		private double yield;

		//予測最大収穫量
		@Persistent 
		private double maxYield;

		//予測最小収穫量
		@Persistent 
		private double minYield;
		
		
		public Predict(Long id, List<Double> temp, List<Double> laytime, List<Double> prec, double yield, double maxYield, double minYield) {
			this.id = id;
			this.temp = temp;
			this.laytime = laytime;
			this.prec = prec;
			this.yield = yield;
			this.maxYield = maxYield;
			this.minYield = minYield;
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public List<Double> getTemp() {
			return temp;
		}


		public void setTemp(List<Double> temp) {
			this.temp = temp;
		}


		public List<Double> getLaytime() {
			return laytime;
		}


		public void setLaytime(List<Double> laytime) {
			this.laytime = laytime;
		}


		public List<Double> getPrec() {
			return prec;
		}


		public void setPrec(List<Double> prec) {
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
