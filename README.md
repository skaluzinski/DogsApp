
# DogsApp

App using is using free APIs to:
=
- get list of breed and allow user to download photos by longClick on photo.
- get list of quotes and save them by clicking on bookmark icon to local room DB
- (not implemented yet) allows user to customize widget based on saved quotes and saved photos

User can:
=
- delete locally saved quotes
- change theme of the app 
- download photos with longClick on photo

Possible upgrades:
=
- using share preferences to save previous theme
- use of caching to not do the same API call needlessly 
- change of returned value of repos to catch errors in a better way
- sharing of quotes and images (via intents possibly - To Be Done)

Todo:
=
- move _binding = null from onDestroy to onDestroyView
- use runCatching in repositories with kotlin.Result
- delete bloated useCases
- resolve N+1 problem of api Calls
- find out whether it makes sense to inject dispatchers into useCases
- TESTS
- checking internet connection to not crash while it's absent :)
- change one-show flow to suspend fun
- change too deeply nested function call
- (optional) change drawables-24 so that it's possible to support previous versions
 

## Tech Stack

**Jetpack** : Room, Navigation, ViewBinding

**Libraries**: Retrofit,Hilt,Flow,Coroutines

**Architecture**: Clean Architecture

**Other**: Material 3
