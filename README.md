# People

Tech stack

* Kotlin
* Espresso
* JUnit
* UiAutomator declaration (could be used for push notifications explicit testing)

Run

* No parallelization this time, just a straightforward run on an emulator/device.

* Tests can be run directly from Android studio -- one by one or by a test class, 
so no configs for CI/CD or the whole project run for now either.

* Emulator/device should have the following developer options disabled: 
Window + Transition animation scale and Animator duration scale.

* Minimal Android SDK is 26, target - 33.

Test coverage
 
* All assertions/actions in tests can be hidden behind the methods from the Helper class, 
but for the sake of time i only did that for those used more than once.

* Several tests only have the outlines to show the intentions to automate with no actual realisation:
both due to the lack of time, and technical questions that stack overflow can't help with. 
This also explains lack of R.id/layout addressing.
