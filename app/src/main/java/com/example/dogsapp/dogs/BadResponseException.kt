package com.example.dogsapp.dogs

import java.lang.Exception

class BadResponseException(result: String) : Exception(result)