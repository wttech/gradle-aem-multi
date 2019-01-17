/**
 * Purpose of this script is to demonstrate some script being run by AEM Groovy Console.
 */

// =====[ Configuration ]=====

DRY_RUN = true // TODO 'false' (to automatically apply changes)
CONTENT_PATHS = ["/content/example", "/content/example-demo"]
MIGRATED_DATE = Calendar.getInstance()

// =====[ Definition ]=====

def debug(message) {
    println message
    log.info(message)
}

def migratePages(def rootPath) {
    def sql = "SELECT * FROM [cq:PageContent] AS s WHERE ISDESCENDANTNODE([$rootPath])"
    for (def contentResource : resourceResolver.findResources(sql, "JCR-SQL2")) {
        def pagePath = contentResource.parent.path
        def props = contentResource.adaptTo(ModifiableValueMap.class)

        if (props["migrated"] == null) {
            debug "Migrating page: $pagePath"
            props["migrated"] = MIGRATED_DATE
        } else {
            debug "Not migrating page: $pagePath"
        }
    }
}

// =====[ Execution ]=====

for (def contentPath : CONTENT_PATHS) {
    migratePages(contentPath)
}

if (!DRY_RUN) {
    resourceResolver.commit()
}
