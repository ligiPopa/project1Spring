package com.example.demo.resources;

public enum DiscountType {
    //ToDO change to not have the same price for all stages
        FullPrice(0),
        StudentPrice(5.50),
        ChildPrice(10.50);
        public final double label;
        private DiscountType(double label) {
            this.label = label;
        }
}
