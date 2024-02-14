package com.app.ecommercial.model.enums;

public enum CategoryType {
    ELECTRONICS("Electronics"),
    FASHION("Fashion"),
    HOME("Home"),
    SPORTS("Sports"),
    BOOKS("Books"),
    BEAUTY("Beauty"),
    TOYS("Toys"),
    FOOD("Food"),
    HEALTH("Health"),
    FURNITURE("Furniture"),
    AUTOMOTIVE("Automotive"),
    JEWELRY("Jewelry"),
    MUSIC("Music"),
    MOVIES("Movies"),
    PET_SUPPLIES("Pet Supplies"),
    ART("Art"),
    TRAVEL("Travel"),
    TECH("Tech"),
    FITNESS("Fitness"),
    GARDEN("Garden"),
    CLOTHING("Clothing"),
    SHOES("Shoes"),
    CRAFTS("Crafts"),
    ELECTRICAL("Electrical"),
    APPLIANCES("Appliances"),
    BEVERAGES("Beverages"),
    PERSONAL_CARE("Personal Care"),
    DIY("DIY"),
    STATIONERY("Stationery"),
    WEDDING("Wedding"),
    HOBBIES("Hobbies"),
    ELECTRONIC_ACCESSORIES("Electronic Accessories"),
    OUTDOOR("Outdoor"),
    BUSINESS("Business"),
    OFFICE_SUPPLIES("Office Supplies"),
    EDUCATION("Education"),
    BABY("Baby"),
    PARTY("Party"),
    SPORTS_EQUIPMENT("Sports Equipment"),
    ANIMALS("Animals"),
    PHOTOGRAPHY("Photography"),
    GAMING("Gaming"),
    COLLECTIBLES("Collectibles"),
    INSTRUMENTS("Instruments"),
    RELIGION("Religion"),
    COLLEGE("College"),
    MEMORABILIA("Memorabilia"),
    VINTAGE("Vintage");

    private final String displayName;

    CategoryType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
