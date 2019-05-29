<h4 align="center">Android Library</h4>

# GenericAdapter [![Tweet](https://img.shields.io/twitter/url/http/shields.io.svg?style=social)](https://twitter.com/intent/tweet?url=https://github.com/manojbhadane/GenericAdapter)

An Easy to use adapter for android

1. No need to create seperate class for adapter
2. No need of viewholder 
3. More readble code


<p align="center">
  <a target="_blank" href="https://android-arsenal.com/api?level=16"><img src="https://img.shields.io/badge/API-16%2B-orange.svg"></a>
  <a target="_blank" href="https://jitpack.io/#manojbhadane/GenericAdapter"><img src="https://jitpack.io/v/manojbhadane/GenericAdapter.svg"></a>
  <a target="_blank" href="https://android-arsenal.com/details/1/7607"><img src="https://img.shields.io/badge/Android%20Arsenal-GenericAdapter-brightgreen.svg?style=flat"></a>
  <a target="_blank" href="https://paypal.me/manojbhadane" title="Donate using PayPal"><img src="https://img.shields.io/badge/paypal-donate-blue.svg" /></a>
</p>


# Medium 
[Please find detail medium post here, Android Generic Adapter](https://medium.com/@manojbhadane/android-generic-recyclerview-adapter-c0024161f1bc)

# Download

This library is available in **jitPack** which is the default Maven repository used in Android Studio.

## Gradle 
**Step 1.** Add it in your root build.gradle at the end of repositories
```
allprojects 
{
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

**Step 2.** Add the dependency in your apps module build.gradle
```
dependencies 
{
	 implementation 'com.github.manojbhadane:GenericAdapter:v1.2'
}
```

# Usage

1. In App level build.gradle 
```
dataBinding {
        enabled true
}
```
2. In Activity/Fragment (Java)
```
 mDataBinding.recylerview.setAdapter(new GenericAdapter<PeopleModel, ListitemMainBinding>(this, arrayList) {
            @Override
            public int getLayoutResId() {
                return R.layout.listitem_main;
            }

            @Override
            public void onBindData(PeopleModel model, int position, ListitemMainBinding dataBinding) {
                dataBinding.txtName.setText(model.getName());
                dataBinding.txtAddress.setText(model.getAddress());
            }

            @Override
            public void onItemClick(PeopleModel model, int position) {

            }
        });
```
3. In Activity/Fragment (Kotlin)
```
 var adapter = object : GenericAdapter<PeopleModel, ListitemMainBinding>(this, arrayList) {
            override fun getLayoutResId(): Int {
                return R.layout.listitem_main
            }

            override fun onBindData(model: PeopleModel?, position: Int, dataBinding: ListitemMainBinding?) {
                dataBinding!!.txtName.text = model.getName()
		dataBinding!!.txtAddress.text = model.getAddress()
            }

            override fun onItemClick(model: PeopleModel?, position: Int) {

            }
        }
	
dataBinding.recyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
dataBinding.recyclerview.adapter = adapter

```

# Bugs or Requests

If you encounter any problems feel free to open an [issue](https://github.com/manojbhadane/GenericAdapter/issues/new?assignees=&labels=&template=bug_report.md). If you feel the library is missing a feature, please raise a [ticket](https://github.com/manojbhadane/EasyRetro/issues/new?assignees=&labels=&template=feature_request.md) on GitHub and I'll look into it. Pull request are also welcome. 

# Spread Some :heart:
[![GitHub followers](https://img.shields.io/github/followers/manojbhadane.svg?style=social&label=Follow)](https://github.com/manojbhadane)  [![Twitter Follow](https://img.shields.io/twitter/follow/manojbhadane.svg?style=social)](https://twitter.com/Manoj_bhadane) 

# About The Author

### Manoj Bhadane

Android & Backend Developer.


<a href="https://medium.com/@manojbhadane"><img src="https://github.com/manojbhadane/Social-Icons/blob/master/medium-icon.png?raw=true" width="60"></a>
<a href="https://stackoverflow.com/users/4034678/manoj-bhadane"><img src="https://github.com/manojbhadane/Social-Icons/blob/master/stackoverflow-icon.png?raw=true" width="60"></a>
<a href="https://twitter.com/Manoj_bhadane"><img src="https://github.com/manojbhadane/Social-Icons/blob/master/twitter-icon.png?raw=true" width="60"></a>
<a href="https://in.linkedin.com/in/manojbhadane"><img src="https://github.com/manojbhadane/Social-Icons/blob/master/linkedin-icon.png?raw=true" width="60"></a>

# If this library helps you in anyway, show your love :heart: by putting a :star: on this project :v:

# License

```
MIT License

Copyright (c) 2019 Manoj Bhadane

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

