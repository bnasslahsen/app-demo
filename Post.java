
@Bean
public RouterFunction<ServerResponse> routes(PostHandler postHandler) {
	return route(GET("/posts").and(queryParam("key1", "value1")).and(queryParam("key2", "value2"))
			.withMetadata("operation", new Operation().operationId("findPostsByKey1AndKey2"))
			.withMetadata("parameter", new Parameter().name("key1").description("My key1 description"))
			.withMetadata("parameter", new Parameter().name("key2").description("My key2 description"))
		        .withMetadata("response", new ApiResponse().responseCode("200").description("This is an operation description").type(Post.class))
			.withMetadata("response", new ApiResponse().responseCode("404").description("item not found"))
		, postHandler::all)
		.andRoute(PUT("/posts/{id}")
			.withMetadata("operation", new Operation().operationId("updatePost")
			.addParametersItem(new Parameter().name("id").in(ParameterIn.PATH.toString()).description("My id description"))
			.responses(new ApiResponses().addApiResponse("202", new ApiResponse().description("OK"))
			.addApiResponse("404", new ApiResponse().description("item to update, not found"))))
		, postHandler::update);
	}



@Bean
public RouterFunction<ServerResponse> routes(PostHandler postHandler) {
	return route(GET("/posts").and(queryParam("key1", "value1")).and(queryParam("key2", "value2")).withMetadata("operation", this::findPostsByKey1AndKey2), postHandler::all)
			.andRoute(PUT("/posts/{id}").withMetadata("operation", this::updatePost), postHandler::update);
}

private Operation findPostsByKey1AndKey2() {
	return new Operation().operationId("findPostsByKey1AndKey2")
			.addParametersItem(new Parameter().name("key1").description("My key1 description"))
			.addParametersItem(new Parameter().name("key2").description("My key2 description"))
			.responses(new ApiResponses()
					.addApiResponse("200", new ApiResponse().description("This is an operation description").type(Post.class))
					.addApiResponse("404", new ApiResponse().description("item not found")));

}

private Operation updatePost() {
	return new Operation().operationId("updatePost")
			.addParametersItem(new Parameter().name("id").in(ParameterIn.PATH.toString()).description("My id description"))
			.responses(new ApiResponses().addApiResponse("202", new ApiResponse().description("OK"))
					.addApiResponse("404", new ApiResponse().description("item to update, not found")));

}

