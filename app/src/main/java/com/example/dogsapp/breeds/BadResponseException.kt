package com.example.dogsapp.breeds

import java.lang.Exception

class BadResponseException(result: String) : Exception(result)