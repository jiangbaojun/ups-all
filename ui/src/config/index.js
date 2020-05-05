import dev from './modules/dev.config'
import prop from './modules/prod.config'
import test from './modules/test.config'
import Constant from './modules/constant'
let Env
switch (process.env.NODE_ENV) {
  case 'development':
    Env = dev
    break
  case 'production':
    Env = prop
    break
  case 'test':
    Env = test
    break
}
export {Env, Constant}