# SignatureHandler
Program to sign and verify signatures using 
* ECC with explicit parameters instead of named curves
* BouncyCastle as a provider
* JDK APIs.

## Requirements
You have to set up a Java Keystore (JKS) in the appropriate format. In my case BouncyCastleKeyStore (BSK).  
Within the store you need to generate 
1. Keypair
2. Certificate

Install Provider either statically within the code (as I did) or dynamically within you JRE. Furhter infos: https://www.bouncycastle.org/wiki/display/JA1/Provider+Installation

## Adjustments 
* Don't forget to change the `keyStorePath` and `keyAlias`
* Alternative algorithms provided by Bouncy Castle can be found here: https://bouncycastle.org/specifications.html
