//
//  view2.swift
//  Assignment 1
//
//  Created by Owen Reynolds on 1/27/22.
//

import SwiftUI

struct view2: View {
    var body: some View {
        VStack {
            Spacer()
            Image(systemName: "ant")
                .resizable()
                .aspectRatio(contentMode: .fit)
                .foregroundColor(Color(red: 20/255, green: 20/255, blue: 20/255))
                .padding()
            Spacer()
        }
        .background(Color(red: 210/255, green: 00/255, blue: 0/255))
    }
}

struct view2_Previews: PreviewProvider {
    static var previews: some View {
        view2()
    }
}
