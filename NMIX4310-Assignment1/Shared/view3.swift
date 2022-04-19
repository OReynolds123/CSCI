//
//  view3.swift
//  Assignment 1
//
//  Created by Owen Reynolds on 1/27/22.
//

import SwiftUI

struct view3: View {
    var body: some View {
        ZStack {
            Rectangle()
                .aspectRatio(contentMode: .fit)
                .overlay(
                    LinearGradient(gradient: Gradient(colors: [.red, .yellow]), startPoint: .topLeading, endPoint: .bottomTrailing)
                )
                .offset(x: 0, y: -210)
            Circle()
                .frame(width: 100, height: 100)
                .foregroundColor(Color(red: 250/255, green: 70/255, blue: 0/255))
            Clouds(offx:-120, offy:-250)
            Clouds(offx:75, offy:-150)
            Clouds(offx:95, offy:-350)
            Rectangle()
                .frame(width: .infinity, height: 450)
                .foregroundColor(Color.blue)
                .offset(x:0, y:225)
            Rectangle()
                .frame(width: .infinity, height: 200)
                .foregroundColor(Color.yellow)
                .offset(x:0, y:350)
            Human()
                .scaleEffect(0.5)
                .offset(x:50, y:300)
            Human()
                .scaleEffect(0.5)
                .rotationEffect(Angle(degrees: 5))
                .offset(x:-50, y:250)
        }
    }
}

struct view3_Previews: PreviewProvider {
    static var previews: some View {
        view3()
    }
}

struct Clouds: View {
    var offx: CGFloat
    var offy: CGFloat
    
    var body: some View {
        ZStack {
            Ellipse()
                .frame(width: 130, height: 110)
                .foregroundColor(Color.white)
                .offset(x: -50, y: 0)
            Ellipse()
                .frame(width: 150, height: 130)
                .foregroundColor(Color.white)
            Ellipse()
                .frame(width: 130, height: 110)
                .foregroundColor(Color.white)
                .offset(x: 50, y: 0)
        }
        .offset(x: offx, y: offy)
    }
}

struct Human: View {
    var body: some View {
        ZStack {
            Ellipse()
                .frame(width: 70, height: 100)
                .foregroundColor(Color(red: 250/255, green: 150/255, blue: 0/255))
                .offset(x:0, y:80)
            Ellipse()
                .frame(width:25, height: 60)
                .foregroundColor(Color(red: 250/255, green: 130/255, blue: 0/255))
                .rotationEffect(.degrees(45))
                .offset(x:-50, y:80)
            Ellipse()
                .frame(width:25, height: 60)
                .foregroundColor(Color(red: 250/255, green: 130/255, blue: 0/255))
                .rotationEffect(.degrees(-45))
                .offset(x:50, y:80)
            Ellipse()
                .frame(width:25, height: 70)
                .foregroundColor(Color(red: 250/255, green: 130/255, blue: 0/255))
                .rotationEffect(.degrees(20))
                .offset(x:-30, y:150)
            Ellipse()
                .frame(width:25, height: 70)
                .foregroundColor(Color(red: 250/255, green: 130/255, blue: 0/255))
                .rotationEffect(.degrees(-20))
                .offset(x:30, y:150)
            Circle()
                .frame(width: 100, height: 100)
                .foregroundColor(Color(red: 230/255, green: 100/255, blue: 0/255))
            Circle()
                .frame(width: 20, height: 20)
                .foregroundColor(Color(red: 255/255, green: 255/255, blue: 255/255))
                .offset(x:-15, y:0)
            Circle()
                .frame(width: 20, height: 20)
                .foregroundColor(Color(red: 255/255, green: 255/255, blue: 255/255))
                .offset(x:15, y:0)
            Circle()
                .frame(width: 6, height: 6)
                .foregroundColor(Color(red: 0/255, green: 0/255, blue: 0/255))
                .offset(x:-15, y:0)
            Circle()
                .frame(width: 6, height: 6)
                .foregroundColor(Color(red: 0/255, green: 0/255, blue: 0/255))
                .offset(x:15, y:0)
        }
    }
}
