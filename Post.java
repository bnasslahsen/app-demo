@Configuration
class PostRouter {

	@Bean
	public RouterFunction<ServerResponse> routes(PostHandler postHandler) {
		return route(GET("/posts1").and(queryParam("key1", "value1")).and(queryParam("key2", "value2"))
						.addMetadata("operation", new Operation().operationId("findByKey1AndKey2"))
						.addMetadata("parameter", new Parameter().name("key1").description("My key1 description"))
						.addMetadata("parameter", new Parameter().name("key2").description("My key2 description"))
						.addMetadata("response", new ApiResponse().responseCode("200").description("This is my description").type(Post.class))
				, postHandler::all)
				.andRoute(PUT("/posts/{id}")
								.addMetadata("operation", new Operation().operationId("update")
										.addParametersItem(new Parameter().name("id").in(ParameterIn.PATH.toString()).description("My key1 description"), new Parameter().name("key2").description("My key2 description"))
										.responses(new ApiResponses().addApiResponse("202", new ApiResponse().description("This is my description"))))
						, postHandler::update);
	}
}
