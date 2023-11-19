package dk.vv.mtogo.order.msvc.api;

public interface ExamplePayloads {
    String LIST_OF_ORDERS = "[\n" +
            "  {\n" +
            "    \"id\": 0,\n" +
            "    \"customerId\": 0,\n" +
            "    \"comment\": \"string\",\n" +
            "    \"orderLines\": [\n" +
            "{\n" +
            "      \"id\": 0,\n" +
            "      \"productId\": 0,\n" +
            "      \"quantity\": 0,\n" +
            "      \"unitGrossPrice\": 0.0,\n" +
            "      \"unitNetPrice\": 0.0,\n" +
            "      \"total\": 0.0,\n" +
            "      \"subTotal\": 0.0\n" +
            "}\n" +
            "    ],\n" +
            "    \"statusId\": 0,\n" +
            "    \"subTotal\": 0,\n" +
            "    \"total\": 0,\n" +
            "    \"addressId\": 0,\n" +
            "    \"supplierId\": 0\n" +
            "  }\n" +
            "]";

    String NEW_ORDER = "{\n" +
            "  \"customerId\": 0,\n" +
            "  \"comment\": \"string\",\n" +
            "  \"orderLines\": [\n" +
            "{\n" +
            "    \"productId\": 0,\n" +
            "    \"quantity\": 0\n" +
            "}\n" +
            "  ],\n" +
            "  \"addressId\": 0,\n" +
            "  \"supplierId\": 0\n" +
            "}\n";

    String CREATED_ORDER =
            "  {\n" +
            "    \"id\": 0,\n" +
            "    \"customerId\": 0,\n" +
            "    \"comment\": \"string\",\n" +
            "    \"orderLines\": [\n" +
            "{\n" +
            "      \"id\": 0,\n" +
            "      \"productId\": 0,\n" +
            "      \"quantity\": 0,\n" +
            "      \"unitGrossPrice\": 0.0,\n" +
            "      \"unitNetPrice\": 0.0,\n" +
            "      \"total\": 0.0,\n" +
            "      \"subTotal\": 0.0\n" +
            "}\n" +
            "    ],\n" +
            "    \"statusId\": 0,\n" +
            "    \"subTotal\": 0,\n" +
            "    \"total\": 0,\n" +
            "    \"addressId\": 0,\n" +
            "    \"supplierId\": 0\n" +
            "  }\n";

}
