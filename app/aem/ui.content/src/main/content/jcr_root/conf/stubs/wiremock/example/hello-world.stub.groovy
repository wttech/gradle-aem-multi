stubs.server.with {
    stubFor(get("/example/hello")
            .willReturn(ok("Hello World!")))

    stubFor(get("/example/hello/json")
            .willReturn(okJson("""{"message": "Hello" }""")))

    stubFor(get("/example/hello/json/file")
            .willReturn(aResponse()
                    .withBodyFile("example/hello-world.json")
                    .withHeader("Content-Type", "application/json")))
}
