/*
 * Copyright (c) 2016 by its authors. Some rights reserved.
 * See the project homepage at: https://sincron.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sincron.atomic

final class AtomicAny[T <: AnyRef] private[atomic] (initialValue: T) extends Atomic[T] {
  private[this] var ref = initialValue

  def getAndSet(update: T): T = {
    val current = ref
    ref = update
    current
  }

  def compareAndSet(expect: T, update: T): Boolean = {
    if (ref == expect) {
      ref = update
      true
    }
    else
      false
  }

  def set(update: T): Unit = {
    ref = update
  }

  def get: T = ref
}

object AtomicAny {
  def apply[T <: AnyRef](initialValue: T): AtomicAny[T] =
    new AtomicAny(initialValue)
}
