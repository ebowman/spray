/*
 * Copyright (C) 2011 Mathias Doenitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.spray.http
package parser

import org.specs.Specification

class QueryParserSpec extends Specification {
  
  "The QueryParser" should {
    "correctly extract complete key value pairs" in {
      QueryParser.parse("key=value") mustEqual Map("key" -> "value")
      QueryParser.parse("key=value&key2=value2") mustEqual Map("key" -> "value", "key2" -> "value2")
    }
    "return an empty Map for an empty query string" in {
      QueryParser.parse("") mustEqual Map()
    }
    "return an empty value for keys without a value following the '=' and keys without following '='" in {
      QueryParser.parse("key=&key2") mustEqual Map("key" -> "", "key2" -> "")
    }
    "throw an HttpException for illegal query strings" in {
      QueryParser.parse("key=&&b") must throwA[HttpException]
    }
  }
  
} 