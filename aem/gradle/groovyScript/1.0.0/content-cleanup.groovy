// ===[ Configuration ]===

def debug(message) {
    println(message)
    log.info(message)
}

def contentRoots = [
    '/content/example/demo',
    '/content/example/live'
]

def cleanContent(root) {
    debug "Cleaning content at root '$root'"
    def sql = "SELECT * FROM [cq:PageContent] AS s WHERE ISDESCENDANTNODE([$root])"
    for (def pageContent : resourceResolver.findResources(sql, "JCR-SQL2")) {
        debug "Cleaning page '${pageContent.path}'"
        Thread.sleep(500) // TODO simulates some repository operation
    }
    debug "Cleaned content at root '$root'"
}

// ===[ Execution ]===

contentRoots.forEach { cleanContent(it) }
