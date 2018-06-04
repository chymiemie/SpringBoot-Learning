package com.iflytek.chy;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class OuterClassJson {

	private String name;

	private int age;

	private D1 d1;

	private List<D2> d2 = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public D1 getD1() {
		return d1;
	}

	public void setD1(D1 d1) {
		this.d1 = d1;
	}

	public List<D2> getD2() {
		return d2;
	}

	public void setD2(List<D2> d2) {
		this.d2 = d2;
	}

	public class D1 {
		private String a1;

		private int b1;

		public String getA1() {
			return a1;
		}

		public void setA1(String a1) {
			this.a1 = a1;
		}

		public int getB1() {
			return b1;
		}

		public void setB1(int b1) {
			this.b1 = b1;
		}

	}

	public class D2 {
		private String a2;
		private int b2;

		public String getA2() {
			return a2;
		}

		public void setA2(String a2) {
			this.a2 = a2;
		}

		public int getB2() {
			return b2;
		}

		public void setB2(int b2) {
			this.b2 = b2;
		}

	}

	public static void main(String[] args) {

		OuterClassJson outClass = new OuterClassJson();
		OuterClassJson.D1 e = outClass.new D1();
		OuterClassJson.D2 f = outClass.new D2();
		OuterClassJson.D2 g = outClass.new D2();

		e.a1 = "张三1111";
		e.b1 = 30;

		f.a2 = "李四22222";
		f.b2 = 40;

		g.a2 = "李四3333";
		g.b2 = 44;

		outClass.name = "王五555";
		outClass.age = 50;
		outClass.d1 = e;
		outClass.d2.add(f);
		outClass.d2.add(g);

		String str = JSON.toJSONString(outClass);
		System.out.println(str);
	}
}
