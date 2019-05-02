# AEM Migration Module

## Description

* automated Groovy Script execution on AEM instances after CRX package deployment
    * on script change (default mode)
    * always (if file name ends with *.always.groovy)
    * with fallbacks (secondary file names ending with *.fallback.groovy)

The main requirement of using this automated migrartion script execution is to have pre-installed on AEM instances [AEM Easy Content Upgrade Package](https://github.com/valtech/aem-easy-content-upgrade) (e.g by `instanceSatisfy` task, it is bundling also [Groovy Console](https://github.com/icfnext/aem-groovy-console)).
Secondary requirement is to have specified Vault hook in CRX package (it is already configured in this module).

Using AECU eliminates a need for manual script execution via [Groovy Console](https://github.com/icfnext/aem-groovy-console) or a need for implementing custom automation. What is also worth to indicate is that this kind of automation works on any environment (even local/dev) and is mostly covered by AEM itself (the trigger is just CRX package installation).
